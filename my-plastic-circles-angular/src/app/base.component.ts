import { Injector } from "@angular/core";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { MessageType } from "./model/message";
import { DiscService } from "./service/disc.service";
import { ManufacturerService } from "./service/manufacturer.service";
import { MessageService } from "./service/message.service";

export abstract class BaseComponent {

  protected fb: FormBuilder;
  protected discService: DiscService;
  protected manufacturerService: ManufacturerService;
  protected messageService: MessageService;
  protected router: Router;
  protected route: ActivatedRoute;

  constructor(injector: Injector) {
    this.fb = injector.get(FormBuilder);
    this.discService = injector.get(DiscService);
    this.manufacturerService = injector.get(ManufacturerService);
    this.messageService = injector.get(MessageService);
    this.router = injector.get(Router);
    this.route = injector.get(ActivatedRoute);
  }

  protected handleError(errorMsg) {
    this.messageService.addMessage({type: MessageType.Error, message: errorMsg})
  }

}