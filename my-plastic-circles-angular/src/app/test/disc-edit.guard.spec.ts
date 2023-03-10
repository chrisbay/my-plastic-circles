import { inject, TestBed } from '@angular/core/testing';
import { ActivatedRoute, convertToParamMap, RouterStateSnapshot } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { DiscEditGuard } from '../discs/disc-edit.guard';

const state: RouterStateSnapshot = jasmine.createSpyObj('RouterStateSnapshot', ['']);

describe('DiscEditGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ DiscEditGuard,
      {
        provide: ActivatedRoute,
        useValue: {snapshot: {paramMap: convertToParamMap({id: 'a'})}}
      } ],
      imports: [ RouterTestingModule ]
    });
  });

  it('should create', inject([DiscEditGuard], (guard: DiscEditGuard) => {
    expect(guard).toBeTruthy();
  }));

  it('should return false for non-numeric id param', inject([DiscEditGuard], (guard: DiscEditGuard) => {
    const route = TestBed.get(ActivatedRoute);
    expect(guard.canActivate(route.snapshot, state)).toBe(false);
  }));
});

describe('DiscEditGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ DiscEditGuard,
      {
        provide: ActivatedRoute,
        useValue: {snapshot: {paramMap: convertToParamMap({id: '-1'})}}
      } ],
      imports: [ RouterTestingModule ]
    });
  });

  it('should return false for negative numeric id param', inject([DiscEditGuard], (guard: DiscEditGuard) => {
    const route = TestBed.get(ActivatedRoute);
    expect(guard.canActivate(route.snapshot, state)).toBe(false);
  }));
});

describe('DiscEditGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ DiscEditGuard,
      {
        provide: ActivatedRoute,
        useValue: {snapshot: {paramMap: convertToParamMap({id: '1'})}}
      } ],
      imports: [ RouterTestingModule ]
    });
  });

  it('should return true for positive numeric id param', inject([DiscEditGuard], (guard: DiscEditGuard) => {
    const route = TestBed.get(ActivatedRoute);
    expect(guard.canActivate(route.snapshot, state)).toBe(true); 
  }));
});