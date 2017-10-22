import {Component, OnInit} from '@angular/core';
import {Template} from '../../../model/template';
import {TemplateClient} from '../../../service/client/template-client.service';
import {FilterMetadata, LazyLoadEvent, MenuItem, Message} from 'primeng/primeng';
import {Page} from '../../../../../core/model/page';
import {Resource} from 'halfred';
import * as _ from 'lodash';
import 'rxjs/add/operator/do';
import {Router} from "@angular/router";


const DEFAULT_PAGE_SIZE = 5;

interface EditCompleteEvent {
  column: any;
  data: any;
  index: number;
}

@Component({
  selector: 'myo-template-list',
  templateUrl: './template-list.component.html',
  styleUrls: ['./template-list.component.css']
})
export class TemplateListComponent implements OnInit {

  messages: Message[] = [];
  buttonItems: MenuItem[];
  elements: Template[];
  selection: Template[];
  page: Page;

  constructor(private client: TemplateClient,
              private router: Router) {
  }

  ngOnInit() {
    this.initButtons();
    this.initElements();
  }

  handleLoadLazy(event: LazyLoadEvent) {
    if (this.page.totalElements === 0) {
      this.loadTemplates(0, this.page.size);
    } else {
      this.page.size = event.rows;
      this.loadTemplates(
        Math.floor(event.first / this.page.size),
        this.page.size,
        event.sortField ? `${event.sortField},${event.sortOrder > 0 ? 'asc' : 'desc'}` : event.sortField,
        event.filters);
    }
  }

  handleEditComplete(event: EditCompleteEvent) {
    this.client.modify$(this.elements[event.index].id, _.omit(event.data, ['_links', 'id']))
      .map((hal: Resource) => hal.original())
      .subscribe(template => this.elements[event.index] = template);
  }

  edit(id: string) {
    this.router.navigate(['templates','edit', id]);
  }

  delete(id: string) {
    this.client.delete$(id)
      .subscribe(() => {
        this.messages = [];
        this.messages.push({severity: 'info', summary: 'Success', detail: 'Data deleted'});
      });
  }

  new() {
    this.router.navigate(['templates','edit']);
  }

  private initElements() {
    this.elements = [];
    this.selection = [];
    this.page = {number: 0, size: DEFAULT_PAGE_SIZE, totalElements: 0, totalPages: 0};
  }

  private loadTemplates(page: number, size: number, sort?: string, filters?: { [a: string]: FilterMetadata; }) {
    (sort ?
      this.client.findAll$(page, size, sort, filters) :
      this.client.findAll$(page, size, null, filters))
      .subscribe(
        (hal: Resource) => {
          this.elements = hal.embeddedArray('templates').map(r => r.original());
          this.page.totalElements = hal['page'].totalElements;
          this.page.totalPages = hal['page'].totalPages
        },
        () => {
          this.initElements();
        }
      );
  }

  private initButtons() {
    this.buttonItems = [
      {label: 'Delete', icon: 'fa-close', command: (event) => {
        console.log(_.values(event))
        this.delete(event);
      }}
    ];
  }

}
