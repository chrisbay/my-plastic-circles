import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { DiscService } from '../service/disc.service';

import { DiscsListComponent } from '../discs/discs-list.component';
import { TestMocks } from './test-utils';

describe('DiscsListComponent', () => {
  let component: DiscsListComponent;
  let fixture: ComponentFixture<DiscsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscsListComponent ],
      imports: [ 
        RouterTestingModule, 
        ReactiveFormsModule,
        HttpClientModule
      ],
      providers: [ 
        { 
          provide: DiscService, 
          useValue: TestMocks.getDiscServiceMock() 
        } 
      ]
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
