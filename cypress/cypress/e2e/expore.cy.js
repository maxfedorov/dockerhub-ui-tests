const image = require('../fixtures/image')

describe('explore page', () => {
    beforeEach(() => {
        cy.visit('https://hub.docker.com/search?q=')
    })

    it('search image', () => {
        const image = 'alpine'
        cy.get('input[data-testid="autocompleteInput"]')
            .clear()
            .type(`${image}{enter}`)
        cy.get('[data-testid="imageSearchResult"]')
            .first()
            .as('firstImage')
        cy.get('@firstImage')
            .find('[data-testid="productBadge"]')
            .should("have.text", "Docker Official Image")
    })

    it('use search filter', () => {
        cy.contains(image.product).parent().parent().click()
        cy.contains(image.name).click()
        cy.url().should('include', image.name)
        cy.title().should('include', image.name)
    })
})