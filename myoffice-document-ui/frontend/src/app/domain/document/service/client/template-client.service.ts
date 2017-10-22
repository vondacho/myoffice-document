import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {parse, Resource} from "halfred";
import {TemplateDto} from "../../model/template";

@Injectable()
export class TemplateClient {

  static ENDPOINT_URL = '/api/document/v1/templates';

  constructor(private http: HttpClient) {
  }

  create$(template: TemplateDto): Observable<Resource> {
    return this.http.post(`${TemplateClient.ENDPOINT_URL}`, template).map(parse);
  }

  modify$(id: string, attributes: { [a: string]: string|number; }, projection: string = ''): Observable<Resource> {
    return this.http.patch(`${TemplateClient.ENDPOINT_URL}/${id}?projection=${projection}`, attributes).map(parse);
  }

  delete$(id: string): Observable<any> {
    return this.http.delete(`${TemplateClient.ENDPOINT_URL}/${id}`);
  }

  findById$(id: string): Observable<Resource> {
    return this.http.get(`${TemplateClient.ENDPOINT_URL}/${id}`).map(parse);
  }

  findAll$(page?: number,
           size?: number,
           sort?: string,
           filters?: { [a: string]: any; },
           projection: string = 'noContent'): Observable<Resource> {
    let query = new HttpParams();
    query = page ? query.append('page', page.toString()) : query;
    query = size ? query.append('size', size.toString()) : query;
    query = sort ? query.append('sort', this.encodeAttribute(sort)) : query;
    query = filters ? query.append('filter', this.encodeFilter(filters)) : query;
    return this.http.get(`${TemplateClient.ENDPOINT_URL}?projection=${projection}`, {params: query}).map(parse);
  }

  private encodeAttribute(field: string): string {
    return field;
  }

  private encodeFilter(filters: { [a: string]: any; }): string {
    let filter = '';
    for (const attribute of Object.keys(filters)) {
      filter += `${(filter.length > 0 ? ',' : '')}${this.encodeAttribute(attribute)}:${filters[attribute].value}`;
    }
    return filter;
  }
}
