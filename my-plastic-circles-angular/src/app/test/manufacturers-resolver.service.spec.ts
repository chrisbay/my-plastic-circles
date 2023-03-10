import { ManufacturerService } from "../service/manufacturer.service";
import { ManufacturersResolver } from "../service/manufacturers-resolver.service";
import { asyncData } from "./async-observable-helpers";

describe('ManufacturersResolver', () => {

  let manufacturerServiceSpy: jasmine.SpyObj<ManufacturerService>;
  let manufactuersResolver: ManufacturersResolver;

  beforeEach(() => {
    manufacturerServiceSpy = jasmine.createSpyObj('ManufacturerService', ['getAll']);
    manufactuersResolver = new ManufacturersResolver(manufacturerServiceSpy);
  });

  it('resolves to the expected manufacturers (calls Manufacturers.getAll)', (done: DoneFn) => {
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

    manufacturerServiceSpy
      .getAll
      .and
      .returnValue(asyncData(expectedManufacturers));

    const state = jasmine.createSpyObj('RouterStateSnapshot', ['']);
    const snapshot = jasmine.createSpyObj('ActivatedRouteSnapshot', ['']);
    manufactuersResolver.resolve(snapshot, state).subscribe({
      next: manufacturersResolved => {
        expect(manufacturersResolved.manufacturers).toBe(expectedManufacturers);
        done();
      },
      error: done.fail
    });

    expect(manufacturerServiceSpy.getAll.calls.count()).toBe(1);
  });

});