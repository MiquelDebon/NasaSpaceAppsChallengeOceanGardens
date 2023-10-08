import { Component, OnInit, inject } from '@angular/core';
import { action1, action2, action3, action4, urlState1, urlState2, urlState3, urlState4 } from '../../models/temes';
import { Action, User, phytoState } from 'src/app/models/interfaces';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { tap } from 'rxjs';
import { phytoStateService } from 'src/app/services/fito-state.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  private authService = inject(AuthService);
  private phytoService = inject(phytoStateService);

 
  user!: User;
  phyto!: phytoState;
  phytoImg = '';

  action1 = action1;
  action2 = action2;
  action3 = action3;
  action4 = action4;

  navigateToActivity(action: Action) {
    this.router.navigate([`activities/${action}`]);
  }

  constructor(private router: Router) {
    this.authService.currentUser$.subscribe(res => {
      this.user = res!;
      if (res!.phytoplankton.health <= 0) {
        this.phytoImg = urlState1;
      } else if (res!.phytoplankton.health < 30) {
        this.phytoImg = urlState2;
      } else if (res!.phytoplankton.health < 65) {
        this.phytoImg = urlState3;
      } else {
        this.phytoImg = urlState4;
      }
    });

      
  //   this.phytoService.phytoState$.pipe(tap(phyto => {
  //     if (phyto.health! <= 0) {
  //       this.phytoImg = urlState1;
  //     } else if (phyto.health! < 30) {
  //       this.phytoImg = urlState2;
  //     } else if (phyto.health! < 65) {
  //       this.phytoImg = urlState3;
  //     } else {
  //       this.phytoImg = urlState4;
  //     }
  // })).subscribe(res => {alert('s"ha actualitzat')});
  }

  ngOnInit() {
  }

}
