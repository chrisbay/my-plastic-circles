import { HttpClient } from "@angular/common/http";
import { DiscService } from "../service/disc.service";
import { asyncData } from "./async-observable-helpers";

describe('DiscService', () => {

  let httpClientSpy: jasmine.SpyObj<HttpClient>;
  let discService: DiscService;
  let discsUrl: string = 'http://localhost:8081/api/disc';

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get', 'post', 'put', 'delete']);
    discService = new DiscService(httpClientSpy);
  });

  it('should return expected discs on GET all (HttpClient called once)', (done: DoneFn) => {
    const expectedDiscs = [{
      id: 1,
      model: 'a',
      manufacturer: {id: 3, name: 'a'},
      speed: 1,
      glide: 1,
      turn: 1,
      fade: 1,
      notes: 'asdf',
      favorite: false
    },
    {
      id: 2,
      model: 'b',
      manufacturer: {id: 4, name: 'b'},
      speed: 1,
      glide: 1,
      turn: 1,
      fade: 1,
      notes: 'fdsa',
      favorite: true
    }];

    httpClientSpy.get.and.returnValue(asyncData(expectedDiscs));

    discService.getAll().subscribe({
      next: discs => {
        expect(discs)
          .withContext('expected discs')
          .toEqual(expectedDiscs);
        done();
      },
      error: done.fail
    });

    expect(httpClientSpy.get.calls.count())
      .withContext('one call')
      .toBe(1);
  });

  it('should return expected disc on GET by id (HttpClient called once)', (done: DoneFn) => {
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

    httpClientSpy
      .get
      .withArgs(`${discsUrl}/${expectedDisc.id}`)
      .and.returnValue(asyncData(expectedDisc));

    discService.get(expectedDisc.id).subscribe({
      next: disc => {
        expect(disc)
          .withContext('expected disc')
          .toEqual(expectedDisc);
        done();
      },
      error: done.fail
    });

    expect(httpClientSpy.get.calls.count())
      .withContext('one call')
      .toBe(1);
  });

  it('should return expected disc on POST (HttpClient called once)', (done: DoneFn) => {
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

    httpClientSpy
      .post
      .and
      .returnValue(asyncData(expectedDisc));

    discService.save(expectedDisc).subscribe({
      next: disc => {
        expect(disc)
          .withContext('expected disc')
          .toEqual(expectedDisc);
          done();
      },
      error: done.fail
    });

    expect(httpClientSpy.post.calls.count())
      .withContext('one call')
      .toBe(1);
  });

  it('should return expected disc on PUT (HttpClient called once)', (done: DoneFn) => {
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

    httpClientSpy
      .put
      .and
      .returnValue(asyncData(expectedDisc));

    discService.update(expectedDisc).subscribe({
      next: disc => {
        expect(disc)
          .withContext('expected disc')
          .toEqual(expectedDisc);
          done();
      },
      error: done.fail
    });

    expect(httpClientSpy.put.calls.count())
      .withContext('one call')
      .toBe(1);
  });

  it('should call HttpClient on DELETE', (done: DoneFn) => {
    httpClientSpy
      .delete
      .withArgs(`${discsUrl}/1`)
      .and.returnValue(asyncData(undefined));

    discService.delete(1).subscribe({
      next: () => {
        done();
      },
      error: done.fail
    });

    expect(httpClientSpy.delete.calls.count())
      .withContext('one call')
      .toBe(1);
  });

});