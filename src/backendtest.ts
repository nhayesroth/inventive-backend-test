import { LookerNodeSDK } from '@looker/sdk-node';

(async () => {
  // create a Node SDK object for API 4.0
  const sdk = LookerNodeSDK.init40()
  // retrieve your user account to verify correct credentials
  const me = await sdk.ok(sdk.me(
    "id, first_name, last_name, display_name, email, personal_space_id, home_space_id, group_ids, role_ids"))
  console.log({me})

  // Custom Inline Query
  const requestParams: Parameters<typeof sdk.run_inline_query>[0] = {
    result_format: 'json',
    body: {
      model: 'thelook_partner',
      view: 'order_items',
      fields: ['order_items.sale_price', 'products.category', 'products.item_name'],
      filters: { 'order_items.sale_price': '>200', 'products.category': 'Pants' }
    },
    cache: true, // use looker cache by default
    limit: 10,
  };

  const results = await sdk.ok(sdk.run_inline_query(requestParams));
  console.log(results);

  // Saved Query for Backend Test
  const lookRequestParams = {
    look_id: '3',
    result_format: 'json',
    cache: true, // use looker cache by default
    limit: 10,
  }

  const lookResults = await sdk.ok(sdk.run_look(lookRequestParams));
  console.log(lookResults);

  await sdk.authSession.logout()
  if (!sdk.authSession.isAuthenticated()) {
    console.log('Logout successful')
  }
})()
