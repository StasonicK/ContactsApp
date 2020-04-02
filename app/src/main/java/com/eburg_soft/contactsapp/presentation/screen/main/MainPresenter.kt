package com.eburg_soft.contactsapp.presentation.screen.main

class MainPresenter : MainContract.Presenter() {
//    override fun loadContactsListByQuery(query: String?, networkAvailable: Boolean) {
//        if (!TextUtils.isEmpty(query)) {
//            if (networkAvailable) {
//                subscribe(
//                    setContactsListInAdapter(contactDao.getContactsByName(query ?: ""))
//
//                )
//                subscribe(
//                    setContactsListInAdapter(contactDao.getContactsByPhone(query ?: ""))
//                )
//            }
//        }
//    }
//
//
//    private fun setContactsListInAdapter(maybe: Maybe<List<Contact>>): Disposable {
//        return maybe
//            .toFlowable()
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMap { Flowable.fromIterable(it) }
//            .doOnNext {
//                view?.addCurrency(
//                    Contact(
//                        it.contactId,
//                        it.contactName,
//                        it.contactPhone,
//                        it.contactHeingt,
//                        it.contactBiography,
//                        it.contactTemperament,
//                        it.contactEducationStart,
//                        it.contactEducationEnd
//                    )
//                )
//            }
//            .doOnComplete {
//                view?.hideLoading()
//            }
//            .subscribe({
//                view?.hideLoading()
//                view?.notifyAdapter()
//            }, {
//                view?.showErrorMessage(it.message)
//                view?.hideLoading()
//                it.printStackTrace()
//            })
//    }
}