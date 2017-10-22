import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class DocumentClient {

    static ENDPOINT_URL = '/api/document/v1/documents';

    constructor(private http: HttpClient) {
    }
}
