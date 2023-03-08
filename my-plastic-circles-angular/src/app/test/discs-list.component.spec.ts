import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { DiscService } from '../service/disc.service';

import { DiscsListComponent } from '../discs/discs-list.component';
import { MessageService } from '../service/message.service';
import { asyncData } from './async-observable-helpers';

describe('DiscsListComponent', () => {
  let component: DiscsListComponent;
  let fixture: ComponentFixture<DiscsListComponent>;
  let discServiceSpy: jasmine.SpyObj<DiscService>;
  let messageServiceSpy: jasmine.SpyObj<MessageService> = jasmine.createSpyObj('MesageService', ['addMessage']);

  beforeEach(async(() => {
    discServiceSpy = jasmine.createSpyObj('DiscService', ['update', 'getAll']);

    const discs = [{
      id: 1,
      model: 'a',
      manufacturer: {id: 3, name: 'a'},
      speed: 1,
      glide: 1,
      turn: 1,
      fade: 1,
      notes: 'asdf',
      favorite: false
    },
    {
      id: 2,
      model: 'b',
      manufacturer: {id: 4, name: 'b'},
      speed: 1,
      glide: 1,
      turn: 1,
      fade: 1,
      notes: 'fdsa',
      favorite: true
    }];

    discServiceSpy.getAll.and.returnValue(asyncData(discs));

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
          useValue: discServiceSpy
        },
        {
          provide: MessageService,
          useValue: messageServiceSpy
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

  it('should toggle favorite field from true to false', (done: DoneFn) => {
    let testDisc = {
      id: 1,
      model: 'a',
      manufacturer: {id: 3, name: 'a'},
      speed: 1,
      glide: 1,
      turn: 1,
      fade: 1,
      notes: 'asdf',
      favorite: true
    };

    testDisc.favorite = false;
    discServiceSpy
      .update
      .withArgs(testDisc)
      .and
      .returnValue(asyncData(testDisc));

    testDisc.favorite = true;
    component.toggleFavoriteStatus(testDisc).subscribe({
      next: done,
      error: done.fail
    })

    expect(testDisc.favorite).toBe(false);
  });

});
