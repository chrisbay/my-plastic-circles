import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { Disc } from '../model/disc';
import { Manufacturer } from '../model/manufacturer';
import { DiscService } from '../service/disc.service';
import { ManufacturerService } from '../service/manufacturer.service';

import { DiscsFormComponent } from '../discs/discs-form.component';
import { TestMocks } from './test-utils';

describe('DiscsFormComponent', () => {
  let component: DiscsFormComponent;
  let fixture: ComponentFixture<DiscsFormComponent>;
  let discServiceStub: Partial<DiscService>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscsFormComponent ],
      imports: [ 
        RouterTestingModule,
        ReactiveFormsModule 
      ],
      providers: [ 
        { 
          provide: DiscService, 
          useValue: discServiceStub 
        },
        { 
          provide: ManufacturerService, 
          useValue: TestMocks.getManufacturerServiceMock() 
        }
      ],
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
