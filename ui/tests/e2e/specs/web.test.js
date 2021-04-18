// https://docs.cypress.io/api/introduction/api.html

describe('Test on regular desktop', () => {
  it('Basic functionality', () => {
    cy.viewport(1920, 1080)

    cy.visit('/')
    
    cy.get('#id-keywords').type('aap');
    cy.get('#id-keywords').contains('AAP');

    cy.wait(1000);
    cy.contains('AAPL (Apple Inc)').click();

    cy.wait(1000);
    cy.get('.dropdown').click();

    cy.wait(500);
    cy.contains('2021-04-15').click();
    
    cy.wait(1000);
    cy.get('.dropdown').click();
    cy.wait(500);
    cy.contains('2021-04-14').click();

    cy.wait(1000);
    cy.get('.dropdown').click();
    cy.wait(500);
    cy.contains('2021-04-13').click();
    
    cy.wait(1000);
    cy.get('.dropdown').click();
    cy.wait(500);
    cy.contains('2021-04-12').click();

    cy.wait(1000);
    cy.get('.dropdown').click();
    cy.wait(500);
    cy.contains('2021-04-09').click();
  })
})
