import { Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { Subject, Subscription, first } from 'rxjs';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { User } from 'src/app/models/interfaces';
import { AuthService } from '../../../services/auth.service';
import { UserForLogin } from '../../../models/interfaces';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginComponent implements OnInit {

  user: User | null = this.authService.currentUser;
  loading = false;
  submitted = false;
  // anima = false;

  constructor(private authService: AuthService,
    private fb: FormBuilder, private route: ActivatedRoute, private router: Router) { }

//Create form
logForm!: FormGroup;

//Function to validate form 

//Variable for validation message
userNotFoundError: boolean = false;

get getName() {
  return this.logForm.get("name");
}

get getPhytoplanktonName() {
  return this.logForm.get("phytoplanktonName");
}
  
get getEmail() {
  return this.logForm.get("email");
}
  
get getPassword() {
  return this.logForm.get("password");
}

  ngOnInit() {
    this.logForm = this.fb.group({
      email: ["", [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: ["", Validators.required]
    });

  }
  

  //function for login submit
  submit(formValue: any) {

    this.submitted = true;
    this.loading = true;

      this.authService.login(formValue).subscribe({
        next: () => {
          this.router.navigate(['dashboard']);
            this.userNotFoundError = false;
        },
        error:
          error => {
            this.userNotFoundError = true;
            this.loading = false;
            console.error(error);
          }
      });
    }
  }
