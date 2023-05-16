const path = require('path')

exports.createPages = async ({ graphql, actions }) => {

  const { data } = await graphql(
    `
      query Posts {
        allMarkdownRemark{
          nodes{
            frontmatter{
              slug
            }
          }
        }
      }
    `
  )

  data.allMarkdownRemark.nodes.forEach( node =>{
    actions.createPage({
      path: node.frontmatter.slug,
      component: path.resolve('./src/templates/posts.js'),
      context: { 
        slug: node.frontmatter.slug 
      }
    })
  })

}
