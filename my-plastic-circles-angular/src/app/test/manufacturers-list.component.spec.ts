import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { ManufacturerService } from '../service/manufacturer.service';
import { TestMocks } from './test-utils';

import { ManufacturersListComponent } from '../manufacturers/manufacturers-list.component';

describe('ManufacturersListComponent', () => {
  let component: ManufacturersListComponent;
  let fixture: ComponentFixture<ManufacturersListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManufacturersListComponent ],
      imports: [ 
        ReactiveFormsModule,
        RouterTestingModule,
        HttpClientModule
      ],
      providers: [ 
        { 
          provide: ManufacturerService, 
          useValue: TestMocks.getManufacturerServiceMock()
        }
      ],
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
