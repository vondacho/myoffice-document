export class Template {
  constructor(public name?: string,
              public content?: string,
              public id?: string,
              public _links?: any) {}
}

export interface TemplateDto {
  name: string;
  content: string;
}

