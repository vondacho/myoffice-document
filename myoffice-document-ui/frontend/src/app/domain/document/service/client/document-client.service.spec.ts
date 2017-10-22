import {inject, TestBed} from '@angular/core/testing';

import {HttpClientTestingModule} from '@angular/common/http/testing';
import {DocumentClient} from "./document-client.service";

describe('DocumentClient', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [DocumentClient]
    });
  });

  it('should be created', inject([DocumentClient], (service: DocumentClient) => {
    expect(service).toBeTruthy();
  }));
});
