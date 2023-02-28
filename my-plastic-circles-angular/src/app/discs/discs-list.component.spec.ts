import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscsListComponent } from './discs-list.component';

describe('DiscsListComponent', () => {
  let component: DiscsListComponent;
  let fixture: ComponentFixture<DiscsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiscsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
