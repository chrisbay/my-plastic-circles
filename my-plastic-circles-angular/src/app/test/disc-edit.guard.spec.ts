import { TestBed, async, inject } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { DiscEditGuard } from '../discs/disc-edit.guard';

describe('DiscEditGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiscEditGuard],
      imports: [ RouterTestingModule ]
    });
  });

  it('should ...', inject([DiscEditGuard], (guard: DiscEditGuard) => {
    expect(guard).toBeTruthy();
  }));
});
