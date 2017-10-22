import {inject, TestBed} from '@angular/core/testing';

import {HttpClientTestingModule} from '@angular/common/http/testing';
import {TemplateClient} from "./template-client.service";

describe('TemplateClient', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TemplateClient]
    });
  });

  it('should be created', inject([TemplateClient], (service: TemplateClient) => {
    expect(service).toBeTruthy();
  }));
});
