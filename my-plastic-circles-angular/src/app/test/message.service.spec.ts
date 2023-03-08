import { MessageType } from "../model/message";
import { MessageService } from "../service/message.service";

describe('MessageService', () => {

  it('should properly add a message', (done: DoneFn) => {
    const service = new MessageService();
    const message = {
      type: MessageType.Info,
      message: 'test'
    };

    service.subscribe({
      next: messages => {
        expect(messages.length).toBe(1);
        expect(messages[0]).toBe(message);
        done();
      },
      error: done.fail
    });

    service.addMessage(message);
  });

  it('should properly clear a message', (done: DoneFn) => {
    const service = new MessageService();
    const message = {
      type: MessageType.Info,
      message: 'test'
    };

    service.addMessage(message);

    service.subscribe({
      next: messages => {
        expect(messages.length).toBe(0);
        done();
      },
      error: done.fail
    });

    service.clearMessage(0);
  });

});