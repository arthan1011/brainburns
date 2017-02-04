import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {DesksComponent} from "./desks.component";
import {DeskDetailComponent} from "./desk-detail.component";
import {DeskListComponent} from "./desk-list.component";
import {DesksRoutingModule} from "./desks-routing.module";
import {DeskService} from "./desk.service";
import {HttpModule} from "@angular/http";
import {DeskNewFormComponent} from "./desk-new-form.component";
import {DeskCommunicationService} from "./desk-communication.service";
import {CardFormComponent} from "./card-form.component";
import {CardService} from "./card.service";

@NgModule({
    imports: [
        CommonModule,
        HttpModule,
        FormsModule,
        DesksRoutingModule
    ],
    declarations: [
        DesksComponent,
        DeskDetailComponent,
        DeskListComponent,
        DeskNewFormComponent,
        CardFormComponent
    ],
    providers: [
        DeskService,
        CardService,
        DeskCommunicationService
    ]
})
export class DesksModule {

}