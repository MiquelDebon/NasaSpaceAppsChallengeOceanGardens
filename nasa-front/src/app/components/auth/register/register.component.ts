import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/interfaces';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


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
      name: ["", Validators.required],
      phytoplanktonName: ["", Validators.required],
      email: ["", [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: ["", Validators.required]
    });

  }
  

  //function for login submit
  submit(formValue: any) {

    this.submitted = true;
    this.loading = true;

    if (this.authService.currentUser) {
      alert("There is a logged user already.");
    } else {
      this.authService.signup(formValue).subscribe({
        next: () => {
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

}
