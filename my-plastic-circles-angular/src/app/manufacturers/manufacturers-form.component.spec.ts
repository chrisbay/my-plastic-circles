import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManufacturersFormComponent } from './manufacturers-form.component';

describe('ManufacturersFormComponent', () => {
  let component: ManufacturersFormComponent;
  let fixture: ComponentFixture<ManufacturersFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManufacturersFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManufacturersFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
