import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { debounceTime } from 'rxjs/operators';
import { Manufacturer } from './manufacturer';
import { ManufacturersService } from './manufacturers.service';

@Component({
  templateUrl: './manufacturers-form.component.html'
})
export class ManufacturersFormComponent implements OnInit {

  manufacturerForm!: FormGroup;
  manufacturer: Manufacturer;
  nameMessage!: string;

  private validationMessages: any = {
    required: 'This field is required',
    minlength: 'Name must be between 2 and 50 characters',
    maxlength: 'Name must be between 2 and 50 characters'
  };

  constructor(private fb: FormBuilder, 
              private manufacturersService: ManufacturersService,
              private router: Router) {}

  ngOnInit() {
    this.manufacturerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]]
    });

    const nameControl = this.manufacturerForm.get('name');
    nameControl.valueChanges.pipe(
      debounceTime(1000)
    ).subscribe(
      value => this.setMessage(nameControl)
    );
  }

  onSubmit(): void {
    this.manufacturersService.save(this.manufacturerForm.value)
      .subscribe({
        next: () => this.router.navigate(['/manufacturers']),
        error: err => console.log(err)
      });
  }

  setMessage(c: AbstractControl): void {
    this.nameMessage = '';
    if ((c.touched || c.dirty) && c.errors) {
      this.nameMessage = Object.keys(c.errors).map(
        key => this.validationMessages[key]).join(' ');
    }
    console.log(this.nameMessage);
  }

}
