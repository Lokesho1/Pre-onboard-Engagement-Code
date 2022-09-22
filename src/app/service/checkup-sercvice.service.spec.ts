import { TestBed } from '@angular/core/testing';

import { CheckupSercviceService } from './checkup-sercvice.service';

describe('CheckupSercviceService', () => {
  let service: CheckupSercviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckupSercviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
