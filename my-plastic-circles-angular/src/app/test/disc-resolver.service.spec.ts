import { TestBed } from "@angular/core/testing";
import { ActivatedRoute, convertToParamMap, RouterStateSnapshot } from "@angular/router";
import { DiscResolver } from "../service/disc-resolver.service";
import { DiscService } from "../service/disc.service";
import { asyncData } from "./async-observable-helpers";

describe('DiscResolver', () => {

  let discServiceSpy: jasmine.SpyObj<DiscService>;
  let discResolver: DiscResolver;
  let route: ActivatedRoute;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {snapshot: {paramMap: convertToParamMap({id: '1'})}}
        }
      ]
    }).compileComponents();
    discServiceSpy = jasmine.createSpyObj('DiscService', ['get']);
    discResolver = new DiscResolver(discServiceSpy);
    route = TestBed.get(ActivatedRoute);
  });

  it('resolve to the expected disc (calls DiscService.get)', (done: DoneFn) => {
    const expectedDisc = {
      id: 1,
      model: 'a',
      manufacturer: {id: 3, name: 'a'},
      speed: 1,
      glide: 1,
      turn: 1,
      fade: 1,
      notes: 'asdf',
      favorite: false
    };

    discServiceSpy
      .get
      .withArgs(1)
      .and
      .returnValue(asyncData(expectedDisc));

    const state: RouterStateSnapshot = jasmine.createSpyObj('RouterStateSnapshot', ['']);
    discResolver.resolve(route.snapshot, state).subscribe({
      next: discResolved => {
        expect(discResolved.disc).toBe(expectedDisc);
        done();
      },
      error: done.fail
    });

    expect(discServiceSpy.get.calls.count()).toBe(1);
  });

});