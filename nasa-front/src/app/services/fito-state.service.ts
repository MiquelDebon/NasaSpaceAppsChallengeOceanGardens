import { HttpClient } from '@angular/common/http';
import { Injectable, inject, OnInit } from '@angular/core';
import { Action, phytoState, SolvedActivity, SolvedActivityResult } from '../models/interfaces';
import { rootEndpoint, sendResultsEndpoint } from '../models/endpoints';
import { catchError, switchMap, tap } from 'rxjs/operators';
import { BehaviorSubject, throwError } from 'rxjs';
import { ModalService } from './modal.service';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class phytoStateService {
  private http = inject(HttpClient);
  private modalService = inject(ModalService);
  private router = inject(Router);
  private authService = inject(AuthService);

  phytoStateSubj = new BehaviorSubject<phytoState>({ name: '', health: 100, co2Consumed: 100, reproductions: 0, inSymbiosis:false });
  phytoState$ = this.phytoStateSubj.asObservable();
  


  sendResults(solvedActivity: SolvedActivity) {
    const body = { id: this.authService.currentUser?.id, action: solvedActivity.action, answer: solvedActivity.points };
    // alert("S'ha enviat: " + body.action + body.id + body.answer);
    console.log(body);
    //s'envia solució, es retorna nou estat que s'assigna a this.phytoState
    this.http.post<phytoState>(rootEndpoint + sendResultsEndpoint, body).pipe(tap(
      res => {
        console.log(res)
        this.authService.currentUserSubject.next(
          {
            id: this.authService.currentUser?.id,
            email: this.authService.currentUser?.email!,
            password: this.authService.currentUser?.password,
            name: this.authService.currentUser?.password!,
            phytoplankton: res!
          })
      }
    ),
      catchError(error => {
        console.error('Error:', error);
        return throwError(() => error);
      })).subscribe(res => { console.log(res) });

    //segons si la resposta és correcta o no obrim modal amb un text o altre
    //AIXÒ HAURÀ D'ANAR DINS DEL SUBSCRIBE QUAN UNIM AMB BACKEND
    let modalTitle = 'Llàstima!'; //per defecte, error
    let modalText = solvedActivity.extraInfo;
    let modalAction = 0 as Action; //per defecte, error
    if (solvedActivity.points == true) {
      modalTitle = 'Resposta correcta!';
      modalAction = solvedActivity.action;
    } 
    this.modalService.openModal(modalTitle, modalText, modalAction);
  
    this.router.navigate(['dashboard']);

  }

  constructor() { }

  OnInit() {
    this.authService.currentUser$.subscribe(user => {
      this.phytoStateSubj.next({
        health: user!.phytoplankton.health,
        co2Consumed: user!.phytoplankton.co2Consumed,
        reproductions: user!.phytoplankton.reproductions,
        inSymbiosis: user!.phytoplankton.inSymbiosis,
        name: user!.phytoplankton.name
      })
    })
  }
}
