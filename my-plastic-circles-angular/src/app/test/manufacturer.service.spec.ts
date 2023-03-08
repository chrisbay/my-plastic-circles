import { HttpClient } from "@angular/common/http";
import { ManufacturerService } from "../service/manufacturer.service";
import { asyncData } from "./async-observable-helpers";

describe('ManufacturerService', () => {

  let httpClientSpy: jasmine.SpyObj<HttpClient>;
  let manufacturerService: ManufacturerService;

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get', 'post']);
    manufacturerService = new ManufacturerService(httpClientSpy);
  });

  it('should return expected manufacturers on GET (HttpClient called once)', (done: DoneFn) => {
    const expectedManufacturers = [
      {
        id: 1,
        name: 'Discraft'
      },
      {
        id: 2,
        name: 'EV-7'
      }
    ];

    httpClientSpy.get.and.returnValue(asyncData(expectedManufacturers));

    manufacturerService.getAll().subscribe({
      next: manufacturers => {
        expect(manufacturers)
          .withContext('expected manufacturers')
          .toEqual(expectedManufacturers);
        done();
      },
      error: done.fail
    });

    expect(httpClientSpy.get.calls.count())
      .withContext('one call')
      .toBe(1);
  });

  it('should return expected manufacturer on POST (HttpClient called once)', (done: DoneFn) => {
    const expectedManufacturer = {
      id: 1,
      name: 'Discraft'
    };

    httpClientSpy
      .post
      .and
      .returnValue(asyncData(expectedManufacturer));

    manufacturerService.save(expectedManufacturer).subscribe({
      next: manufacturer => {
        expect(manufacturer)
          .withContext('expected manufacturer')
          .toEqual(expectedManufacturer);
          done();
      },
      error: done.fail
    });

    expect(httpClientSpy.post.calls.count())
      .withContext('one call')
      .toBe(1);
  });

});