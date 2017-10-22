import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DocumentComponentsModule} from "./document-components.module";
import {TemplateListComponent} from "./template-list/template-list.component";
import {TemplateEditComponent} from "./template-edit/template-edit.component";

const routes: Routes = [
  {
    path: 'templates',
    children: [
      {path: '', redirectTo: 'list', pathMatch: 'full'},
      {path: 'list', component: TemplateListComponent},
      {path: 'edit', component: TemplateEditComponent},
      {path: 'edit/:id', component: TemplateEditComponent}
    ]
  }
];

@NgModule({
  imports: [
    DocumentComponentsModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class DocumentRoutingModule {
}
