import { Component, Injector, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, Validators } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { BaseComponent } from '../base.component';
import { Disc, DiscResolved } from '../model/disc';
import { Manufacturer, ManufacturersResolved } from '../model/manufacturer';

@Component({
  templateUrl: './discs-form.component.html'
})
export class DiscsFormComponent extends BaseComponent implements OnInit {

  discForm: FormGroup;
  pageTitle: string = 'New Disc';
  isNew: boolean;
  manufacturers: Manufacturer[];
  disc: Disc;
  modelMessage: string = '';

  private validationMessages: any = {
    required: 'This field is required',
    minlength: 'Model must be between 2 and 50 characters',
    maxlength: 'Model must be between 2 and 50 characters'
  };

  constructor(injector: Injector) { 
    super(injector);
  }

  ngOnInit() {

    this.discForm = this.fb.group({
      model: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      manufacturer: ['', [Validators.required]],
      speed: ['', [Validators.required, Validators.min(1), Validators.max(14)]],
      glide: ['', [Validators.required, Validators.min(1), Validators.max(7)]],
      turn: ['', [Validators.required, Validators.min(-5), Validators.max(1)]],
      fade: ['', [Validators.required, Validators.min(0), Validators.max(5)]],
      notes: ''
    });

    this.route.paramMap.subscribe(
      () => {
        this.isNew = this.route.snapshot.data['isNew'];
        const discResolved: DiscResolved = this.route.snapshot.data['disc'];
        const manufacturersResolved: ManufacturersResolved = this.route.snapshot.data['manufacturers'];
        if (discResolved.error || manufacturersResolved.error) {
          this.handleError('An error occured while retrieving the selected disc');
          this.router.navigate(['/discs']);
          return;
        }

        this.manufacturers = manufacturersResolved.manufacturers;
        this.disc = discResolved.disc;

        if (!this.isNew) {
          const selectedManufacturer = this.manufacturers.filter(m => m.id === this.disc.manufacturer.id)[0];
          this.disc.manufacturer = selectedManufacturer;
        }
        this.discForm.patchValue(this.disc);
      }
    );

    const modelControl = this.discForm.get('model');
    modelControl.valueChanges.pipe(
      debounceTime(1000)
    ).subscribe(
      value => this.setMessage(modelControl)
    );
    
  }

  isInvalid(fieldName: string): boolean {
    const control = this.discForm.get(fieldName);
    if (control === null) return false;
    return (control.touched || control.dirty) && !control.valid;
  }

  onSubmit(isNew: boolean): void {

    if (isNew) {
      this.discService.save(this.discForm.value)
        .subscribe({
          next: () => this.router.navigate(['/discs']),
          error: err => this.handleError(err)
        });
    } else {
      const disc = this.discForm.value;
      disc.id = this.disc.id;
      this.discService.update(disc)
        .subscribe({
          next: () => this.router.navigate(['/discs']),
          error: err => this.handleError(err)
        });
    }
  }

  onDelete(): void {
    this.discService.delete(this.disc.id)
      .subscribe({
        next: () => this.router.navigate(['/discs']),
        error: err => this.handleError(err)
      });
  }

  setMessage(c: AbstractControl): void {
    this.modelMessage = '';
    if ((c.touched || c.dirty) && c.errors) {
      this.modelMessage = Object.keys(c.errors).map(
        key => this.validationMessages[key]).join(' ');
    }
  }

}
