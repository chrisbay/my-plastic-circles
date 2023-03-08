import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable } from 'rxjs';
import { MessageCenterComponent } from '../message-center/message-center.component';
import { MessageComponent } from '../message-center/message.component';
import { Message } from '../model/message';
import { MessageService } from '../service/message.service';


describe('MessageCenterComponent', () => {
  let component: MessageCenterComponent;
  let fixture: ComponentFixture<MessageCenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessageCenterComponent, MessageComponent ],
      providers: [ MessageService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessageCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
