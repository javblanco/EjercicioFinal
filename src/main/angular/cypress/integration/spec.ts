describe('My First Test', () => {
  it('Visits the initial project page', () => {
    cy.visit('/index')
    cy.contains('HEL-ARTE')
  })
})

describe('Tests para estilos/obras', () => {
  it('crear estilos', () => {
    cy.visit('/tipo/list')
    cy.get('.btn').contains('Crear').click();
    cy.url().should('include', 'tipo');
    cy.get('#nombre').type('Angel');
    cy.get('#descripcion').type('Menudo artista');
    cy.get('#caracteristicas').type('Le mola el pokemon go');
    cy.get('#epoca').type('Siglo XX');
    cy.get('#artistas').type('Angel otra vez');
    cy.get(':nth-child(17)').click();
    cy.get('.btn-success').click();
  })

  it('crear obras', () => {
    cy.visit('/obra/list')
    cy.get('.btn').contains('Crear').click();
    cy.url().should('include', 'obra');
    cy.get('#nombre').type('Las meninas');
    cy.get('#autor').type('Javier Fernandez');
    cy.get('#descripcion').type('JAvier Fernandez era pintor');
    cy.get('#fecha').type('1998');
    cy.get(':nth-child(16)').click();
    cy.get('.btn-success').click();
    
  })
})


