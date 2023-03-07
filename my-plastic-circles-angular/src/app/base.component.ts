import { Injector } from "@angular/core";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { MessageType } from "./model/message";
import { DiscsService } from "./service/discs.service";
import { ManufacturersService } from "./service/manufacturers.service";
import { MessagesService } from "./service/messages.service";

export abstract class BaseComponent {

  protected fb: FormBuilder;
  protected discsService: DiscsService;
  protected manufacturersService: ManufacturersService;
  protected messagesService: MessagesService;
  protected router: Router;
  protected route: ActivatedRoute;

  constructor(injector: Injector) {
    this.fb = injector.get(FormBuilder);
    this.discsService = injector.get(DiscsService);
    this.manufacturersService = injector.get(ManufacturersService);
    this.messagesService = injector.get(MessagesService);
    this.router = injector.get(Router);
    this.route = injector.get(ActivatedRoute);
  }

  protected handleError(errorMsg) {
    this.messagesService.addMessage({type: MessageType.Error, message: errorMsg})
  }

}