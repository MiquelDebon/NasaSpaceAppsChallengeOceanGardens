import { Injectable } from '@angular/core';
import { Action } from '../models/interfaces';
import { activitiesList } from 'src/assets/activitiesList';

@Injectable({
  providedIn: 'root'
})
export class ActivitiesService {

constructor() { }

  getActivity(actionType: Action) {
    if (actionType != 0) {
      let randomNum: number = Math.floor(Math.random() * (activitiesList[actionType].length));
      return activitiesList[actionType][randomNum];
    } else {
      return activitiesList[1][0];
    }
  }

  

}
