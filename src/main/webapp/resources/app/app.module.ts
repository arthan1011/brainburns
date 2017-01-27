import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {AppComponent} from "./app.component";
import {DashboardComponent} from "./dashboard.component";
import {AppRoutingModule} from "./app-routing.module";
import {DesksModule} from "./desks/desks.module";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        DesksModule
    ],
    declarations: [
        AppComponent,
        DashboardComponent
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule {
}
