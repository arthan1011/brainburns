import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {AppComponent} from "./app.component";
import {DashboardComponent} from "./dashboard.component";
import {AppRoutingModule} from "./app-routing.module";
import {DesksModule} from "./desks/desks.module";
import {NotificationService} from "./notification.service";
import {NotificationPopupComponent} from "./notification.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        DesksModule
    ],
    declarations: [
        AppComponent,
        DashboardComponent,
        NotificationPopupComponent
    ],
    bootstrap: [
        AppComponent
    ],
    providers: [
        NotificationService
    ]
})
export class AppModule {
}
