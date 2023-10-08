import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Action } from '../models/interfaces';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  private modalVisibilitySubject = new BehaviorSubject<{
    visible: boolean,
    text1: string,
    text2: string,
    action: Action
  }>({ visible: false, text1: '', text2: '', action: 1 });
  modalVisibility$ = this.modalVisibilitySubject.asObservable();

  constructor() { }

  openModal(text1: string, text2: string, action:Action): void {
    this.modalVisibilitySubject.next({ visible: true, text1:text1, text2:text2, action:action });
  }

  closeModal(): void {
    this.modalVisibilitySubject.next({ visible: false, text1: '', text2: '', action:1});
  }

}

