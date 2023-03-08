import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { DiscService } from '../service/disc.service';
import { ManufacturerService } from '../service/manufacturer.service';

import { DiscsFormComponent } from '../discs/discs-form.component';

describe('DiscsFormComponent', () => {
  let component: DiscsFormComponent;
  let fixture: ComponentFixture<DiscsFormComponent>;
  let discServiceSpy: jasmine.SpyObj<DiscService>;
  let manufacturerServiceSpy: jasmine.SpyObj<ManufacturerService>;

  beforeEach(async(() => {
    discServiceSpy = jasmine.createSpyObj('DiscService', ['get']);
    manufacturerServiceSpy = jasmine.createSpyObj('ManufacturerService', ['getAll'])
    TestBed.configureTestingModule({
      declarations: [ DiscsFormComponent ],
      imports: [ 
        RouterTestingModule,
        ReactiveFormsModule 
      ],
      providers: [ 
        {
          provide: ManufacturerService,
          useValue: manufacturerServiceSpy
        },
        {
          provide: DiscService,
          useValue: discServiceSpy
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
