import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'orders',
        loadChildren: () => import('./orders/orders.module').then(m => m.RevalOrdersModule)
      },
      {
        path: 'order-comments',
        loadChildren: () => import('./order-comments/order-comments.module').then(m => m.RevalOrderCommentsModule)
      },
      {
        path: 'order-documents',
        loadChildren: () => import('./order-documents/order-documents.module').then(m => m.RevalOrderDocumentsModule)
      },
      {
        path: 'transactions',
        loadChildren: () => import('./transactions/transactions.module').then(m => m.RevalTransactionsModule)
      },
      {
        path: 'transaction-details',
        loadChildren: () => import('./transaction-details/transaction-details.module').then(m => m.RevalTransactionDetailsModule)
      },
      {
        path: 'lookup-types',
        loadChildren: () => import('./lookup-types/lookup-types.module').then(m => m.RevalLookupTypesModule)
      },
      {
        path: 'lookups',
        loadChildren: () => import('./lookups/lookups.module').then(m => m.RevalLookupsModule)
      },
      {
        path: 'transaction-codes',
        loadChildren: () => import('./transaction-codes/transaction-codes.module').then(m => m.RevalTransactionCodesModule)
      },
      {
        path: 'transaction-code-params',
        loadChildren: () => import('./transaction-code-params/transaction-code-params.module').then(m => m.RevalTransactionCodeParamsModule)
      },
      {
        path: 'system-access',
        loadChildren: () => import('./system-access/system-access.module').then(m => m.RevalSystemAccessModule)
      },
      {
        path: 'organizations',
        loadChildren: () => import('./organizations/organizations.module').then(m => m.RevalOrganizationsModule)
      },
      {
        path: 'companies',
        loadChildren: () => import('./companies/companies.module').then(m => m.RevalCompaniesModule)
      },
      {
        path: 'branches',
        loadChildren: () => import('./branches/branches.module').then(m => m.RevalBranchesModule)
      },
      {
        path: 'providers',
        loadChildren: () => import('./providers/providers.module').then(m => m.RevalProvidersModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class RevalEntityModule {}
