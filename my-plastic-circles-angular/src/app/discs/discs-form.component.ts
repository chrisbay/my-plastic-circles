import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { debounceTime } from 'rxjs/operators';
import { Manufacturer, ManufacturersResolved } from '../manufacturers/manufacturer';
import { ManufacturersService } from '../manufacturers/manufacturers.service';
import { Disc, DiscResolved } from './disc';
import { DiscsService } from './discs.service';

@Component({
  templateUrl: './discs-form.component.html'
})
export class DiscsFormComponent implements OnInit {

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

  constructor(private fb: FormBuilder,
              private discsService: DiscsService,
              private manufacturersService: ManufacturersService,
              private router: Router,
              private route: ActivatedRoute) { }

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
      params => {
        this.isNew = this.route.snapshot.data['isNew'];
        const discResolved: DiscResolved = this.route.snapshot.data['disc'];
        const manufacturersResolved: ManufacturersResolved = this.route.snapshot.data['manufacturers'];
        if (discResolved.error || manufacturersResolved.error) {
          // TODO -  handle error
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
      this.discsService.save(this.discForm.value)
        .subscribe({
          next: () => this.router.navigate(['/discs']),
          error: err => console.log(err)
        });
    } else {
      const disc = this.discForm.value;
      disc.id = this.disc.id;
      this.discsService.update(disc)
        .subscribe({
          next: () => this.router.navigate(['/discs']),
          error: err => console.log(err)
        });
    }
  }

  onDelete(): void {
    this.discsService.delete(this.disc.id)
      .subscribe({
        next: () => this.router.navigate(['/discs']),
        error: err => console.log(err)
      });
  }

  setMessage(c: AbstractControl): void {
    this.modelMessage = '';
    if ((c.touched || c.dirty) && c.errors) {
      this.modelMessage = Object.keys(c.errors).map(
        key => this.validationMessages[key]).join(' ');
    }
    console.log(this.modelMessage);
  }

}
