export interface Manufacturer {
  id: number | null;
  name: string;
}

export interface ManufacturersResolved {
  manufacturers: Manufacturer[] | null;
  error?: string;
}