import { TestBed, async, inject } from '@angular/core/testing';

import { DiscEditGuard } from './disc-edit.guard';

describe('DiscEditGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiscEditGuard]
    });
  });

  it('should ...', inject([DiscEditGuard], (guard: DiscEditGuard) => {
    expect(guard).toBeTruthy();
  }));
});
