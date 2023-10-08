import { Injectable, inject } from '@angular/core';
import { User, UserForLogin, UserForSignup } from '../models/interfaces';
import { BehaviorSubject, Observable, Subscription, catchError, of, tap, throwError } from 'rxjs';
import { rootEndpoint } from '../models/endpoints';
import { HttpClient } from '@angular/common/http';
import { dummyPhyto } from '../models/phyto-dummy';
import { phytoStateService } from './fito-state.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);

  //Subscription for currentUser
  currentUser: User | null = null; //current logged in user
  currentUserSubject = new BehaviorSubject<User | null>(null);
  currentUser$ = this.currentUserSubject.asObservable();
  subscUser: Subscription = this.currentUser$.subscribe(res => this.currentUser = res);

  constructor() { }

  getLocalStorageUser() {
    if (!this.currentUser && localStorage.getItem("currentUserNASA") != undefined) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUserNASA")!);
    this.currentUserSubject.next(this.currentUser);
    console.log("current user gotten from localStorage");
    //part repetida de navbar component:
      this.router.navigate(['dashboard']);
    } 
    return of(this.currentUser);
  }
  
  login(formValue: UserForLogin) {
    // alert('usuari introduït: ' + formValue.email + ' i ' + formValue.password);

    //recorda posar tipus de la funció
    return this.http.post<User>(rootEndpoint + `auth/login`, { email: formValue.email, password: formValue.password }).pipe(
      tap((user: User) => {
        localStorage.setItem("currentUserNASA", JSON.stringify(user));
        this.currentUserSubject.next(user); //phyto-state service ho rep i actualitza phyto-state
          console.log("New user logged in: " +  user);
          console.log(user);
          // this.navAfterLogin();
        
      }),
        catchError((error) => {
          if (error.status === 400) {
            console.log(error);
          } else {
            alert('Error inesperat. Si us plau, intenti-ho més tard.');
          }
          return throwError(() => new Error(error));
      })
    );
  }

  signup(userFormValue: UserForSignup): Observable<any> {
    // alert('Signup amb: ' + userFormValue.name + userFormValue.email +  userFormValue.password + userFormValue.phytoplanktonName);
    console.log(userFormValue);
    //al component s'haurà de navegar a login component
    return this.http.post<User>(rootEndpoint + `auth/register`, userFormValue).pipe(
      tap(() => {
        this.login({ email: userFormValue.email, password: userFormValue.password }).subscribe(res => {
          this.router.navigate(['/video']);
        }
        );
    }),
      catchError((error) => {
        if (error.status === 400) {
          console.log(error);
        } else {
          alert('Error inesperat. Si us plau, intenti-ho més tard.');
        }
        return throwError(() => new Error(error));
      })
    );
  }

  logout() {
    this.http.post(rootEndpoint + 'auth/logout', this.currentUser).subscribe(res => {
      // alert('resposta: ' + res);
      console.log('resposta: ' + res);
      this.router.navigate(['/home']);
   });
  }

}
