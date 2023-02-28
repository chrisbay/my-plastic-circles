import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManufacturersListComponent } from './manufacturers-list.component';

describe('ManufacturersListComponent', () => {
  let component: ManufacturersListComponent;
  let fixture: ComponentFixture<ManufacturersListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManufacturersListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManufacturersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
