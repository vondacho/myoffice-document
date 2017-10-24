import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  ContextMenuModule,
  DataTableModule,
  GrowlModule,
  MessagesModule,
  PaginatorModule,
  SplitButtonModule
} from 'primeng/primeng';
import {TemplateEditComponent} from './template-edit/template-edit.component';
import {TemplateListComponent} from "./template-list/template-list.component";
import {CKEditorModule} from "ng2-ckeditor";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    DataTableModule,
    PaginatorModule,
    ContextMenuModule,
    MessagesModule,
    GrowlModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    CKEditorModule
  ],
  declarations: [
    TemplateListComponent,
    TemplateEditComponent
  ],
  exports: [
    TemplateListComponent,
    TemplateEditComponent
  ]
})
export class DocumentComponentsModule {
}
