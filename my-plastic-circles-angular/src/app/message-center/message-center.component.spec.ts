import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MessageCenterComponent } from './message-center.component';
import { MessageComponent } from './message.component';


describe('MessageCenterComponent', () => {
  let component: MessageCenterComponent;
  let fixture: ComponentFixture<MessageCenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessageCenterComponent, MessageComponent ]
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
