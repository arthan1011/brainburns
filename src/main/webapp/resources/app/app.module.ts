import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {AppComponent} from "./app.component";
import {CardFormComponent} from "./card-form.component";
import {DashboardComponent} from "./dashboard.component";
import {AppRoutingModule} from "./app-routing.module";
import {DesksComponent} from "./desks.component";
import {DeskDetailComponent} from "./desk-detail.component";
import {DeskListComponent} from "./desk-list.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        CardFormComponent,
        DashboardComponent,
        DesksComponent,
        DeskDetailComponent,
        DeskListComponent
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule {
}
