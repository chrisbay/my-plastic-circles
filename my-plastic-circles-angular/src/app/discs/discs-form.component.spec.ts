import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscsFormComponent } from './discs-form.component';

describe('DiscsFormComponent', () => {
  let component: DiscsFormComponent;
  let fixture: ComponentFixture<DiscsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiscsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
