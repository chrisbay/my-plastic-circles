import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule, Title } from '@angular/platform-browser';
import { filter, map } from "rxjs/operators";

import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DiscsModule } from './discs/discs.module';
import { HomeComponent } from './home/home.component';
import { ManufacturersModule } from './manufacturers/manufacturers.module';
import { MessageCenterComponent } from './message-center/message-center.component';
import { MessageComponent } from './message-center/message.component';
import { PageNotFoundComponent } from './page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PageNotFoundComponent,
    MessageCenterComponent,
    MessageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    DiscsModule,
    ManufacturersModule,
    AppRoutingModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 

  private titleBase: string = 'My Plastic Circles - ';

  constructor (private router: Router, private activatedRoute: ActivatedRoute, private titleService: Title) {
    this.router.events.pipe(
        filter(event => event instanceof NavigationEnd),
        map(() => {
            let child = this.activatedRoute.firstChild;
            while (child) {
                if (child.firstChild) {
                    child = child.firstChild;
                } else if (child.snapshot.data &&    child.snapshot.data['title']) {
                    return child.snapshot.data['title'];
                } else {
                    return null;
                }
            }
            return null;
        })
    ).subscribe( (data: any) => {
        if (data) {
            this.titleService.setTitle(this.titleBase + data);
        }
    });
  }

}
