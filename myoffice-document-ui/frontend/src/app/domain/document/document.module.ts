import {NgModule} from '@angular/core';
import {DocumentClient} from './service/client/document-client.service';
import {TemplateClient} from "./service/client/template-client.service";
import {DocumentRoutingModule} from "./component/primeng/document-routing.module";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
    imports: [
        DocumentRoutingModule,
        HttpClientModule
    ],
    providers: [
        DocumentClient,
        TemplateClient,
    ]
})
export class DocumentModule {
}
