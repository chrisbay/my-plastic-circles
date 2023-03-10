import { Component, Injector, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, Validators } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { AbstractBaseComponent } from '../abstract-base.component';
import { Manufacturer } from '../model/manufacturer';
import { MessageType } from '../model/message';
import { MessageService } from '../service/message.service';

@Component({
  templateUrl: './manufacturers-form.component.html'
})
export class ManufacturersFormComponent extends AbstractBaseComponent implements OnInit {

  manufacturerForm!: FormGroup;
  manufacturer: Manufacturer;
  nameMessage!: string;

  private validationMessages: any = {
    required: 'This field is required',
    minlength: 'Name must be between 2 and 50 characters',
    maxlength: 'Name must be between 2 and 50 characters'
  };

  constructor(injector: Injector, messageService: MessageService) {
    super(injector);
  }

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
    this.manufacturerService.save(this.manufacturerForm.value)
      .subscribe({
        next: manufacturer => {
          this.messageService.addMessage({
            type: MessageType.Success, 
            message: `<b>${manufacturer.name}</b> was added to the system`
          });
          this.router.navigate(['/manufacturers'])
        },
        error: err => this.handleError(err)
      });
  }

  setMessage(c: AbstractControl): void {
    this.nameMessage = '';
    if ((c.touched || c.dirty) && c.errors) {
      this.nameMessage = Object.keys(c.errors).map(
        key => this.validationMessages[key]).join(' ');
    }
  }

}
