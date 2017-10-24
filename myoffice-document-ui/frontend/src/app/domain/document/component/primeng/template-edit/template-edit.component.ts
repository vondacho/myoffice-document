import {Component, OnInit} from '@angular/core';
import {Template, TemplateDto} from '../../../model/template';
import {ActivatedRoute} from '@angular/router';
import {TemplateClient} from '../../../service/client/template-client.service';
import {Resource} from 'halfred';
import * as _ from 'lodash';
import {MenuItem, Message} from "primeng/primeng";
import 'rxjs/add/operator/switchMap';
import {empty} from "rxjs/observable/empty";

@Component({
  selector: 'myo-template-edit',
  templateUrl: './template-edit.component.html',
  styleUrls: ['./template-edit.component.css']
})
export class TemplateEditComponent implements OnInit {

  template: Template = new Template();

  messages: Message[] = [];
  buttonItems: MenuItem[];

  constructor(private route: ActivatedRoute,
              private client: TemplateClient) { }

  ngOnInit() {
    this.initButtons();
    this.route.params
      .switchMap(params => params['id'] ? this.client.findById$(params['id']) : empty())
      .map((hal: Resource) => hal.original())
      .subscribe(template =>  {
        this.sleep(500).then(() => {
          this.template = template
        })
      });
  }

  save(value: TemplateDto) {
    if (this.template.id) {
      this.client.modify$(this.template.id, _.omit(value, ['_links', 'id']))
        .subscribe(template => {
          this.template = template;
          this.showSuccess();
        },
        (error) => {
          this.showError();
        });
    } else {
      this.client.create$(value)
        .subscribe(template => {
          this.template = template;
          this.showSuccess();
        },
        (error) => {
          this.showError();
        });
    }
  }

  onChange(event) {}

  onBlur(event) {}

  onFocus(event) {}

  onReady(event) {}

  private sleep(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
  }

  private cancel() {

  }

  showError(message: string = 'An error occurred when performing the operation') {
    this.messages = [];
    this.messages.push({severity:'warn', summary:'Warning', detail:message});
  }

  private initButtons() {
    this.buttonItems = [
      {label: 'Delete', icon: 'fa-close', command: () => {
        this.delete();
      }}
    ];
  }

  private notify(...restOfMessages: any[]) {
    restOfMessages.forEach(m => {
      this.messages.push(m);
    })
  }

  private notifyOnly(...restOfMessages: any[]) {
    this.messages = [];
    this.notify(restOfMessages);
  }

  showSuccess(message: string = 'The operation has been performed successfully') {
    this.messages = [];
    this.messages.push({severity:'success', summary:'Success', detail:message});
  }

  private delete() {
    this.client.delete$(this.template.id)
      .subscribe(
        () => {
          this.template = new Template();
          this.showSuccess();
        },
        (error) => {
          this.showError();
        });
  }
}
