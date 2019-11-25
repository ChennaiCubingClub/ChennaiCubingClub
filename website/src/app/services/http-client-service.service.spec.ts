import { TestBed } from '@angular/core/testing';

import { HttpClientServiceService } from './http-client-service.service';

describe('HttpClientServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpClientServiceService = TestBed.get(HttpClientServiceService);
    expect(service).toBeTruthy();
  });
});
